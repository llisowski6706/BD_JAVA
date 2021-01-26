public class produkty {
    private float id;
    private String nazwisko, adres, telefon, imie, data;
    private byte[] zdjecie;

    public produkty(float id, String imie, String nazwisko, String adres, String telefon, String data, byte[]foto)
    {
        this.id=id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.adres=adres;
        this.telefon=telefon;
        this.data=data;
        this.zdjecie=foto;
    }

    public float getId(){ return id;
    }

    public String getImie() { return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getData() {
        return data;
    }

    public byte[] getFoto() {
        return zdjecie;
    }

}
