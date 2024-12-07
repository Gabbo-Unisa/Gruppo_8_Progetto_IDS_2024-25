/**
 * @file FileManager.java
 *
 * @brief Classe per la gestione dell'importazione ed esportazione della rubrica.
 *
 * Questa classe fornisce funzionalità per importare ed esportare la rubrica da e verso file .vcf.
 *
 * @author Gabriele Pannuto
 * @date December 07, 2024
 * @version 2.0
 */


package rubrica.Models;

public class FileManager {
    private Rubrica r;

    /**
     * @brief Costruttore della classe FileManager.
     *
     * @param[in] r Il riferimento alla rubrica.
     *
     * @post crea un oggetto FileManager con un riferimento alla rubrica.
     */
    public FileManager(Rubrica r) {
        this.r = r;
    }

    /**
     * @brief Importa una rubrica da file .vcf.
     *
     * @param[in] path È il percorso verso il file .vcf che contiene la rubrica da importare.
     *
     * @pre Il path si riferisce ad un file .vcf.
     * @post La rubrica viene popolata da file.
     **/
    public void importaRubrica(String path){

    }

    /**
     * @brief Esporta la rubrica su file .vcf.
     *
     * @param[in] path È il percorso verso la directory in cui scrivere il file .vcf.
     * @param[in] nomeFile Nome del file che conterrà la rubrica.
     *
     * @pre Il path si riferisce ad una directory.
     * @post la rubrica viene salvata in un file .vcf nella directory selezionata.
     * */
    public void esportaRubrica(String path, String nomeFile){

    }
}
