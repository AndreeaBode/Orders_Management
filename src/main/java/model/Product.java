package model;


public class Product {
    private int id;
    private String nume;
    private int pret;
    private int cantitate;

    /**
     *
     */
    public Product(int id, String nume, int pret, int cantitate) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public Product() {
        this.id = 0;
        this.nume ="";
        this.pret = 0;
        this.cantitate = 0;
    }
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNume() {
        return nume;
    }

    /**
     *
     * @param nume
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     *
     * @return
     */
    public int getPret() {
        return pret;
    }

    /**
     *
     * @param pret
     */
    public void setPret(int pret) {
        this.pret = pret;
    }

    /**
     *
     * @return
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     *
     * @param cantitate
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
