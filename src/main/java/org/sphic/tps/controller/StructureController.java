package org.sphic.tps.controller;

import org.sphic.tps.model.Contour;
import org.sphic.tps.model.Structure;
import org.sphic.tps.service.DAO.StructureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/structure")
public class StructureController {
    private StructureDAO structureDao;

    @Autowired
    public StructureController(StructureDAO dao) {
        this.structureDao = dao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Structure Get(@PathVariable int id) {

        Structure structure = structureDao.findById(id);

        return structure;
    }

    @RequestMapping(value = "/{id}/contours", method = RequestMethod.GET)
    public Set<Contour> GetContours(@PathVariable int id) {

        Structure structure = structureDao.findById(id);

        return structure.getContours();
    }

    @RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
    public void Post(@PathVariable int id, @PathVariable String description) {
        Structure structure = structureDao.findById(id);

        structure.setDescription(description);

        structureDao.save(structure);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void Put(@RequestBody Structure structure) {

        structureDao.save(structure);
    }
}
