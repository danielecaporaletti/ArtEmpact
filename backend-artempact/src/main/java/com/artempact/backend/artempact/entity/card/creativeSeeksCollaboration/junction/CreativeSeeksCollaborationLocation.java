package com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.junction;

import com.artempact.backend.artempact.entity.LocalityInterface;
import com.artempact.backend.artempact.entity.card.creativeSeeksCollaboration.CreativeSeeksCollaboration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "artempact", name = "CreativeSeeksCollaborationLocation")
public class CreativeSeeksCollaborationLocation implements LocalityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "province", nullable = false, length = 2)
    private String province;

    @JsonIgnore
    @Column(name = "lat")
    private Double lat;

    @JsonIgnore
    @Column(name = "lon")
    private Double lon;

    // `@ManyToOne` indica che molte `CreativeSeeksCollaborationLocation` possono essere associate a un singolo `CreativeSeeksCollaboration`
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creative_seek_collaboration_id")
    private CreativeSeeksCollaboration creativeSeeksCollaboration;
}
