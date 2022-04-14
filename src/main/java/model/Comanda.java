package model;


public class Comanda {
    private int id;
    private int idClient;
    private int idProdus;
    private int cantitate;

    public Comanda(int id, int idClient, int idProdus, int cantitate) {
        this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }
    public Comanda() {
        this.id = 0;
        this.idClient = 0;
        this.idProdus = 0;
        this.cantitate = 0;
    }

    /**#
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
    public int getIdClient() {
        return idClient;
    }

    /**
     *
     * @param idClient
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     *
     * @return
     */
    public int getIdProdus() {
        return idProdus;
    }

    /**
     *
     * @param idProdus
     */
    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idProdus=" + idProdus +
                ", cantitate=" + cantitate +
                '}';
    }
}
