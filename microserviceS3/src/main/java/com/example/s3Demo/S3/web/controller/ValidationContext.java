package com.example.s3Demo.S3.web.controller;

import com.example.s3Demo.S3.exceptions.DB_ColumnNotFoundException;
import com.example.s3Demo.S3.exceptions.RequestException;
import com.example.s3Demo.repositorySQL.DBService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Getter
@Setter
public class ValidationContext {

    String typeOfCard;
    String cardId;
    String fileType;
    String userId;
    String tableProfileName;
    String userTypeCapitalized;
    String tableCardName = "";
    String contextId;
    String tableName;

    private static final Map<String, String> VALID_FILE_TYPES = Map.of(
            "photo", "photo",
            "video", "video",
            "photopremium", "photoPremium",
            "document", "document",
            "photoproject", "photoProject"
    );

    private static final Map<String, String> VALID_USER_TYPES = Map.of(
            "creative", "Creative",
            "business", "Business"
    );

    private static final Map<String, String> CARD_TYPE_MAPPINGS = Map.of(
            "businessseekscreative", "BusinesSseeksCreative",
            "creativeseeksbusiness", "CreativeSeeksBusiness",
            "creativeseekscollaboration", "CreativeSeeksCollaboration"
    );

    // Constructor
    public ValidationContext(String typeOfCard, String cardId, String type, JwtAuthenticationToken auth, DBService dbService) throws RequestException, DB_ColumnNotFoundException {

        // Check the null value in not required fields and convert to void
        this.typeOfCard = typeOfCard != null ? typeOfCard : "";
        System.out.println(typeOfCard);
        System.out.println(this.typeOfCard);
        this.cardId = cardId != null ? cardId : "";

        // validate "type" field
        this.fileType = validateFileType(type);

        // validate "userId" inside JWT token
        this.userId = validateUserId(auth);

        // validate "user_type" (Creative|Business) inside JWT token, then save the table name for profiles
        this.userTypeCapitalized = validateUserType(auth);
        this.tableProfileName = "Profile" + userTypeCapitalized;

        // validate "typeOfCard" (attention, it can be void)
        // validate "cardId", the cardId must belong to the current user
        if (!this.typeOfCard.isEmpty()) {
            this.tableCardName = validateTypeOfCard(this.typeOfCard);
            dbService.checkUserIdAtCardId(userId, userTypeCapitalized, cardId, tableCardName);
        }

        this.contextId = this.typeOfCard.isEmpty() ? userId : cardId;
        this.tableName = this.typeOfCard.isEmpty() ? tableProfileName : tableCardName;
    }

    private static String validateFileType(String type) throws RequestException {
        return Optional.ofNullable(VALID_FILE_TYPES.get(type.toLowerCase()))
                .orElseThrow(() -> new RequestException("Invalid file type: " + type));
    }

    private static String validateUserId(JwtAuthenticationToken token) throws RequestException {
        return Optional.ofNullable(token.getToken().getClaimAsString("sub"))
                .filter(not(String::isEmpty))
                .orElseThrow(() -> new RequestException("User ID is missing in the JWT token."));
    }

    private static String validateUserType(JwtAuthenticationToken token) throws RequestException {
        return Optional.ofNullable(token.getToken().getClaimAsString("user_type"))
                .map(String::toLowerCase)
                .flatMap(type -> Optional.ofNullable(VALID_USER_TYPES.get(type)))
                .orElseThrow(() -> new RequestException("The profileType is wrong in the JWT token."));
    }

    private static String validateTypeOfCard(String typeOfCard) throws RequestException {
        return Optional.ofNullable(CARD_TYPE_MAPPINGS.get(typeOfCard.toLowerCase()))
                .orElseThrow(() -> new RequestException("The typeOfCard (" + typeOfCard + ") is invalid."));
    }
}
