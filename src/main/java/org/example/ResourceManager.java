package org.example;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
public class ResourceManager{



    @Autowired
    private KatRepository katRepository;

    @Autowired
    private OfisDao ofisDao;
    @Autowired
    private KatDao katDao;

    @GetMapping
    public void entry(HttpServletResponse response) throws IOException {
        Ofis ofis = new Ofis();
//        ofis.setId(12L);
//        Kat kat = new Kat();
//        kat.setKatNo(1);
//        ofis.setKat(kat);
        ofis.setKiraCarpani(3.0);
        ofis.setMetrekare(300.0);
        ofis.setSonOdeme(ZonedDateTime.now().plusMonths(1));
        ofis.reCalculate();
        ofisDao.persist(ofis);
        response.sendRedirect("/home");
    }

    @GetMapping("/home")
    public Ofis[] home(){

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
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Ofis newOfis = new Ofis(ofisKat,
                metrekare,
                kiraCarpani,
                ZonedDateTime.now().plusMonths(1),
                30);
        ofisDao.persist(newOfis);
        katRepository.addOfis(ofisKat,newOfis);

        return ofisDao.getAll();
    }

    @GetMapping("/ofisSil")
    public Ofis ofisSil(@RequestParam(value = "ofisId") Long id){
        return ofisDao.deleteById(id);
    }

    @GetMapping("/ofisGuncelle")
    public Ofis ofisGuncelle(@RequestParam(value = "ofisId") Long id,
                             @RequestParam(value = "metrekare", required = false) Double metrekare,
                             @RequestParam(value = "kiraCarpani",required = false) Double kiraCarpani,
                             @RequestParam (value = "kat", required = false) Integer kat){

        Ofis ofis = ofisDao.findById(id);
        if(metrekare != null){
            ofis.setMetrekare(metrekare);
        }
        if(kiraCarpani != null){
            ofis.setKiraCarpani(kiraCarpani);
        }
        if(kat != null){
            Kat katObj = katDao.getById(kat);
            ofis.setKat(katObj);
        }
        ofis.reCalculate();
        ofisDao.update(ofis);
        return ofis;
    }


}