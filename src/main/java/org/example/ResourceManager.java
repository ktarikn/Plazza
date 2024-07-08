package org.example;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceManager{


    @Autowired
    private KatRepository katRepository;

    @Autowired
    private OfisDao ofisDao;
    @Autowired
    private KatDao katDao;

    @GetMapping("/home")
    public Ofis[] home(){
        Ofis ofis = new Ofis();
//        ofis.setId(12L);
//        Kat kat = new Kat();
//        kat.setKatNo(1);
//        ofis.setKat(kat);
        ofis.setMetrekare(300.0);
        ofis.setSonOdeme(ZonedDateTime.now().plusDays(30));
        ofisDao.persist(ofis);
        return ofisDao.getAll();
    };

    @GetMapping("/ofisEkle")
    public Ofis[] ofisEkle(@RequestParam(value = "metrekare") Double metrekare,
                           @RequestParam(value = "kiraCarpani",defaultValue = "1.0") Double kiraCarpani,
                           @RequestParam (value = "kat") int kat)
    {
        Kat ofisKat;
        try {
             ofisKat = katRepository.getInstanceWithNo(kat);
        } catch (KatNotFoundExpection e) {
            throw new RuntimeException(e);
        }
        Ofis newOfis = new Ofis(ofisKat,
                metrekare,
                kiraCarpani,
                ZonedDateTime.now().plusMonths(1),
                30);
//        List<Ofis> ofisler = ofisKat.getOfisler();
        List<Ofis> ofisler = new ArrayList<>();
        ofisler.add(newOfis);
        ofisKat.setOfisler(ofisler);
//        katDao.merge(ofisKat);
        ofisDao.persist(newOfis);
        return ofisDao.getAll();
    }
}