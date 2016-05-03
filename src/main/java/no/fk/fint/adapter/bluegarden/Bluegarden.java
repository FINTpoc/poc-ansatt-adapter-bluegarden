package no.fk.fint.adapter.bluegarden;

import no.fk.Ansatt;
import no.skate.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Bluegarden {
    private final Map<Identifikator, Ansatt> employees = new HashMap<>();

    @PostConstruct
    public void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
        Date fodselsdato = null;
        try {
            fodselsdato = dateFormat.parse("27/12-1971");
        } catch (ParseException e) {
            throw new RuntimeException();
        }

        Ansatt nils = new Ansatt(new Personnavn("Nils", "Olsen"), Kjonn.MANN, Landkode.NO, fodselsdato, Sivilstand.ENKE_ELLER_ENKEMANN);
        nils.addIdentifikator(new Identifikator("fodselsnummer", "12345678901"));
        nils.setKontaktinformasjon(new Kontaktinformasjon("nils.olsen@hfk.no", "90909090", "90909090"));
        nils.setAvdeling("IKT avdelingen");
        employees.put(nils.getIdentifikatorer().get(0), nils);

        Ansatt elisabeth = new Ansatt(new Personnavn("Elisabeth", "Hansen"), Kjonn.KVINNE, Landkode.NO, fodselsdato, Sivilstand.GIFT);
        elisabeth.addIdentifikator(new Identifikator("ansattnummer", "123"));
        elisabeth.setKontaktinformasjon(new Kontaktinformasjon("elisabeth.hansen@hfk.no", "91919191", "91919191"));
        elisabeth.setAvdeling("Personalavdelingen");
        employees.put(elisabeth.getIdentifikatorer().get(0), elisabeth);

        Ansatt nora = new Ansatt(new Personnavn("Nora", "Johansen"), Kjonn.KVINNE, Landkode.SE, fodselsdato, Sivilstand.GJENLEVENDE_PARTNER);
        nora.addIdentifikator(new Identifikator("fodselsnummer", "23456789012"));
        nora.setKontaktinformasjon(new Kontaktinformasjon("nora.johansen@hfk.no", "92929292", "92929292"));
        nora.setAvdeling("Skeisvang videregående skole");
        employees.put(nora.getIdentifikatorer().get(0), nora);

        Ansatt line = new Ansatt(new Personnavn("Line", "Haraldseth"), Kjonn.KVINNE, Landkode.NO, fodselsdato, Sivilstand.SKILT);
        line.addIdentifikator(new Identifikator("fodselsnummer", "34567890123"));
        line.setKontaktinformasjon(new Kontaktinformasjon("line.haraldseth@hfk.no", "93939393", "93939393"));
        line.setAvdeling("Seksjon for kvalitet, analyse og dimensjonering");
        employees.put(line.getIdentifikatorer().get(0), line);

        Ansatt trygve = new Ansatt(new Personnavn("Trygve", "Minde"), Kjonn.MANN, Landkode.NO, fodselsdato, Sivilstand.GIFT);
        trygve.setAvdeling("Rådmannens stab");
        trygve.setKontaktinformasjon(new Kontaktinformasjon("trygve.minde@hfk.no", "123456789", "123456789"));
        trygve.setKontaktinformasjon(new Kontaktinformasjon("trygve.minde@gmail.no", "94949494", "94949494"));
        trygve.addIdentifikator(new Identifikator("fodselsnummer", "45678901234"));
        employees.put(trygve.getIdentifikatorer().get(0), trygve);

    }

    public List<Ansatt> getEmployees() {
        return new ArrayList<>(employees.values());
    }
}
