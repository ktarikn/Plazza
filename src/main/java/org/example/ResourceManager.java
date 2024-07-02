package org.example;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class ResourceManager{

    @Autowired
    private OfisDao ofisDao;

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
                           @RequestParam(value = "kiraCarpani",defaultValue = "1.0") Double kiraCarpani)
    {
        return null;
    }
}