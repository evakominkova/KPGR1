package cz.uhk.auta.data;

/**
 * Rozhranní pro persistenci dat
 */

public interface PersistenceManager {

    /**
     * Ulozi polozky v evidenci
     * @param evidenceAut
     * @throws PersistenceException
     */

    void ulozitVse (EvidenceAut evidenceAut) throws PersistenceException;

    /**
     * Nacte vsechny polozky ze zdroje do evidence
     * @return
     * @throws PersistenceException
     */

    EvidenceAut nacistVse() throws PersistenceException;
}
