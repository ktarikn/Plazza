package org.example;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZoneIdSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class Ofis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private Double metrekare;

    @Column(name = "kira_carpani")
    private Double kiraCarpani;

    @Column(precision = 10 ,scale = 2 )
    private BigDecimal kira;

    @JsonSerialize(using = OdemeZamaniSerializer.class)
    private ZonedDateTime sonOdeme;

    private int payIntervalnDays;

    @ManyToOne
    @JsonBackReference
    private Kat kat;

    public Ofis(Kat kat,Double metrekare, Double kiraCarpani, ZonedDateTime ilkOdeme, int payIntervalnDays) {
        this.metrekare = metrekare;
        this.kiraCarpani = kiraCarpani;
        this.payIntervalnDays = payIntervalnDays;
        this.sonOdeme = ilkOdeme.plusDays(payIntervalnDays);
        this.kat = kat;
        this.kira = BigDecimal.valueOf(metrekare*kiraCarpani);

    }
    public Ofis(){

    }


}
