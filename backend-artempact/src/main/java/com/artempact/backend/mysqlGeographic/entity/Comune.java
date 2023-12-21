package com.artempact.backend.mysqlGeographic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "gi_comuni_cap")
public class Comune {

    @Id
    @Column(name = "codice_istat", nullable = false, length = 12)
    private String codiceIstat;

    @Column(name = "denominazione_ita_altra", length = 191)
    private String denominazioneItaAltra;

    @Column(name = "denominazione_ita", length = 191)
    private String denominazioneIta;

    @Column(name = "denominazione_altra", length = 191)
    private String denominazioneAltra;

    @Column(name = "cap", length = 10)
    private String cap;

    @Column(name = "sigla_provincia", length = 4)
    private String siglaProvincia;

    @Column(name = "denominazione_provincia", length = 50)
    private String denominazioneProvincia;

    @Column(name = "tipologia_provincia", length = 100)
    private String tipologiaProvincia;

    @Column(name = "codice_regione", length = 4)
    private String codiceRegione;

    @Column(name = "denominazione_regione", length = 50)
    private String denominazioneRegione;

    @Column(name = "tipologia_regione", length = 30)
    private String tipologiaRegione;

    @Column(name = "ripartizione_geografica", length = 20)
    private String ripartizioneGeografica;

    @Column(name = "flag_capoluogo", length = 4)
    private String flagCapoluogo;

    @Column(name = "codice_belfiore", length = 8)
    private String codiceBelfiore;

    @Column(name = "lat", precision = 13, scale = 7)
    private BigDecimal lat;

    @Column(name = "lon", precision = 13, scale = 7)
    private BigDecimal lon;

    @Column(name = "superficie_kmq", precision = 10, scale = 4)
    private BigDecimal superficieKmq;

}
