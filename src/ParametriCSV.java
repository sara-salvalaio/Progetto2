import java.io.Serializable;

public class ParametriCSV implements Serializable {
    public String regione;
    public String provincia;
    public String comune;
    public int longitudine;
    public int latitudine;
    public String anno;

    public ParametriCSV(String regione, String provincia, String comune, int longitudine, int latitudine, String anno) {
        this.regione = regione;
        this.provincia = provincia;
        this.comune = comune;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
        this.anno = anno;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public int getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(int longitudine) {
        this.longitudine = longitudine;
    }

    public int getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(int latitudine) {
        this.latitudine = latitudine;
    }

    public String getAnno() {
        return anno;
    }
    public void setAnno(String anno) {
        this.anno = anno;
    }

    @Override
    public String toString() {
        return "ParametriCSV{" +
                "regione='" + regione + '\'' +
                ", provincia='" + provincia + '\'' +
                ", comune='" + comune + '\'' +
                ", longitudine=" + longitudine +
                ", latitudine=" + latitudine +
                ", anno='" + anno + '\'' +
                '}';
    }
}