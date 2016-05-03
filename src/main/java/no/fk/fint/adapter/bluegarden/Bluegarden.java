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

        Ansatt gunnar = new Ansatt(new Personnavn("Gunnar", "Olsen"), Kjonn.MANN, Landkode.NO, fodselsdato, Sivilstand.ENKE_ELLER_ENKEMANN);
        gunnar.addIdentifikator(new Identifikator("fodselsnummer", "12345678901"));
        gunnar.setKontaktinformasjon(new Kontaktinformasjon("gunnar.olsen@hfk.no", "90909090", "90909090"));
        gunnar.setAvdeling("IKT avdelingen");
        employees.put(gunnar.getIdentifikatorer().get(0), gunnar);

        Ansatt emma = new Ansatt(new Personnavn("Emma", "Hansen"), Kjonn.KVINNE, Landkode.NO, fodselsdato, Sivilstand.GIFT);
        emma.addIdentifikator(new Identifikator("ansattnummer", "123"));
        emma.setKontaktinformasjon(new Kontaktinformasjon("emma.hansen@hfk.no", "91919191", "91919191"));
        emma.setAvdeling("Personalavdelingen");
        employees.put(emma.getIdentifikatorer().get(0), emma);

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

        Ansatt aksel = new Ansatt(new Personnavn("Aksel", "Minde"), Kjonn.MANN, Landkode.NO, fodselsdato, Sivilstand.GIFT);
        aksel.setAvdeling("Rådmannens stab");
        aksel.setKontaktinformasjon(new Kontaktinformasjon("aksel.minde@hfk.no", "123456789", "123456789"));
        aksel.setKontaktinformasjon(new Kontaktinformasjon("aksel.minde@hfk.no", "94949494", "94949494"));
        aksel.addIdentifikator(new Identifikator("fodselsnummer", "45678901234"));
        employees.put(aksel.getIdentifikatorer().get(0), aksel);

    }

    public List<Ansatt> getEmployees() {
        return new ArrayList<>(employees.values());
    }
}
