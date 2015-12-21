package org.sphic.Controller;

import org.sphic.Model.Structure;
import org.sphic.Service.DAO.StructureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/structure")
public class StructureController {
    private StructureDao structureDao;

    @Autowired
    public StructureController(StructureDao dao) {
        this.structureDao = dao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Structure Get(@PathVariable int id) {

        Structure structure = structureDao.get(Structure.class, id);

        return structure;
    }

    @RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
    public void Post(@PathVariable int id, @PathVariable String description) {
        Structure structure = structureDao.get(Structure.class, id);

        structure.setDescription(description);

        structureDao.save(structure);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public int Put(@RequestBody Structure structure) {

        return structureDao.save(structure);
    }
}
