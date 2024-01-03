package com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.junctionTables;

import com.artempact.backend.mysqlArtempact.entity.profile.junctionTable.Photo;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "artempact", name = "PhotoCreative")
public class PhotoCreative extends Photo {

    public PhotoCreative(String photoLink) {
        super(photoLink);
    }

    // `@ManyToOne` indica che molte `Photo` possono essere associate a un singolo `ProfileCreative`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_creative_id")
    private ProfileCreative profileCreative;
}
