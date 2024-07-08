package org.example;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;

import java.lang.reflect.Array;
import java.util.Arrays;

public class KatRepository implements Repository<Kat, Integer> {

    Kat[] katlar;
    Kat[] yerAltiKatlar;

    @Autowired
    KatDao katDao;

    public KatRepository() {
        katlar = new Kat[1];
    }

    public Kat getInstanceWithNo(int katNo) throws KatNotFoundExpection {
        if (validateKat(katNo)){ //makes sure either the floor exists or it is 1 floor away (no minecraft logic)

            if(katNo<0 ) { //below ground
                if (yerAltiKatlar[negativeKat(katNo)] == null) { //new floor
                    Kat kat = new Kat();
                    katDao.persist(kat);
                    kat.setKatNo(katNo);
                    yerAltiKatlar[negativeKat(katNo)] = kat;
                }
                return (yerAltiKatlar[negativeKat(katNo)]);
            }

            else{// above ground
                if (katlar[katNo] == null) { //new floor
                    Kat kat = new Kat();
                    kat.setKatNo(katNo);
                    katDao.persist(kat);
                    katlar[katNo] = kat;
                }
                return katlar[katNo];
            }

        }
        throw new KatNotFoundExpection();
    }

    private boolean validateKat(int katNo) {
        if(katNo<katlar.length){
            return true;
        }
        if(katNo==katlar.length){
            katlar = Arrays.copyOf(katlar, katlar.length + 1);
            return true;
        }
        if(katNo<0){

            if(yerAltiKatlar == null) {
                yerAltiKatlar = new Kat[1];
                return true;
            }
            if(yerAltiKatlar.length>(-katNo+1)){
                return true;
            }
            if(yerAltiKatlar.length==(-katNo+1)){
            return true;
            }
        }
        return false;

    }
    private int negativeKat(int katNo){
        return -katNo-1;
    }
}
