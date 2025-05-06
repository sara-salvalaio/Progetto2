import java.io.Serializable;

public class Faro implements Serializable {
    public String regione;
    public String provincia;
    public String comune;
    public double longitudine;
    public  double latitudine;
    public int annoInserimento;
    public String dataEOra;
    public String nome;

    public Faro(String regione, String provincia, String comune, double longitudine, double latitudine, int annoInserimento, String dataEOra, String nome) {
        this.regione = regione;
        this.provincia = provincia;
        this.comune = comune;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
        this.annoInserimento = annoInserimento;
        this.dataEOra = dataEOra;
        this.nome = nome;
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

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public int getAnnoInserimento() {
        return annoInserimento;
    }

    public void setAnnoInserimento(int annoInserimento) {
        this.annoInserimento = annoInserimento;
    }

    public String getDataEOra() {
        return dataEOra;
    }

    public void setDataEOra(String dataEOra) {
        this.dataEOra = dataEOra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Faro{" +
                "regione='" + regione + '\'' +
                ", provincia='" + provincia + '\'' +
                ", comune='" + comune + '\'' +
                ", longitudine=" + longitudine +
                ", latitudine=" + latitudine +
                ", annoInserimento=" + annoInserimento +
                ", dataEOra='" + dataEOra + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}