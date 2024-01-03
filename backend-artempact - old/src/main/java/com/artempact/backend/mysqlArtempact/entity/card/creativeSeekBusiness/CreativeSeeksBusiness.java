package com.artempact.backend.mysqlArtempact.entity.card.creativeSeekBusiness;

import com.artempact.backend.mysqlArtempact.entity.card.Card;
import com.artempact.backend.mysqlArtempact.entity.lookupEntity.TypeOfBusiness;
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
@Table(schema = "artempact", name = "CreativeSeeksBusiness")
public class CreativeSeeksBusiness extends Card {

    @NotBlank(message = "The positionDescription cannot be empty")
    @Size(max = 280, message = "The positionDescription exceeds 280 characters")
    @Column(name = "positionDescription", nullable = false, length = 280)
    private String positionDescription;

    //`@ManyToOne` indica che molti `CreativeSeeksBusiness` possono essere associati a un singolo `TypeOfBusiness`
    @NotBlank(message = "The identifyBusinesType cannot be empty")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identifyBusinesType_id", referencedColumnName = "id")
    private TypeOfBusiness identifyBusinesType;

    // LIST OF LOCALITY
}
