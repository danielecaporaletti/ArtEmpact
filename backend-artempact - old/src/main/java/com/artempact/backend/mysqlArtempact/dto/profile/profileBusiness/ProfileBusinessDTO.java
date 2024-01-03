package com.artempact.backend.mysqlArtempact.dto.profile.profileBusiness;

import com.artempact.backend.mysqlArtempact.dto.profile.ProfileDTO;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.junctionTable.CreativeSearchLocations;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProfileBusinessDTO extends ProfileDTO {

    @Size(max = 100, message = "The businessName exceeds 100 characters")
    private String businessName;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `TypeOfCreative`
    private Short typeOfBusinessId;

    //`@ManyToOne` indica che molti `ProfileCreative` possono essere associati a un singolo `WorkPreference`
    private Short workPreferenceId;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molte `PhotoBusiness`
    private Set<String> photoBusinessesLinks;

    // `@OneToMany` indica che un singolo `ProfileCreative` può essere associato a molte `CreativeSearchLocations`
    private List<CreativeSearchLocations> creativeSearchLocations;
}
