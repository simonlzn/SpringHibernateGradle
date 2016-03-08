package org.sphic.tps.controller;

import org.sphic.tps.model.Structure;
import org.sphic.tps.model.StructureSet;
import org.sphic.tps.service.DAO.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/structureset")
public class StructureSetController {
    private Dao dao;

    @Autowired
    public StructureSetController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StructureSet Get(@PathVariable int id) {
        StructureSet structureSet = dao.get(StructureSet.class, id);

        return structureSet;
    }

    @RequestMapping(value = "/{id}/structures", method = RequestMethod.GET)
    public Set<Structure> GetStructures(@PathVariable int id) {
        StructureSet structureSet = dao.get(StructureSet.class, id);

        return structureSet.getStructures();
    }

    @RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
    public void Post(@PathVariable int id, @PathVariable String description) {
        StructureSet structureSet = dao.get(StructureSet.class, id);

        structureSet.setDescription(description);

        dao.save(structureSet);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public int Put(@RequestBody StructureSet structureSet) {
        return (Integer) dao.save(structureSet);
    }
}
