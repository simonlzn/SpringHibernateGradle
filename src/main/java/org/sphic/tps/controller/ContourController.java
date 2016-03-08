package org.sphic.tps.controller;

import org.sphic.tps.model.Contour;
import org.sphic.tps.model.Slice;
import org.sphic.tps.model.Structure;
import org.sphic.tps.service.DAO.ContourDAO;
import org.sphic.tps.service.DAO.SliceDAO;
import org.sphic.tps.service.DAO.StructureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contour")
public class ContourController {
    private ContourDAO contourDao;
    private StructureDAO structureDAO;
    private SliceDAO sliceDAO;

    @Autowired
    public ContourController(ContourDAO contourDao, StructureDAO structureDAO, SliceDAO sliceDAO) {
        this.contourDao = contourDao;
        this.structureDAO = structureDAO;
        this.sliceDAO = sliceDAO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Contour Get(@PathVariable int id) {
        Contour contour = contourDao.findById(id);
        return contour;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "sliceId")
    public List<Contour> GetListBySlice(@RequestParam("sliceId") int sliceId) {
        List<Contour> contours = contourDao.getBySliceId(sliceId);

        return contours;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "structureId")
    public List<Contour> GetListByStructure(@RequestParam("structureId") int structureId) {
        List<Contour> contours = contourDao.getByStructureId(structureId);

        return contours;
    }

    @RequestMapping(value = "/{id}/color/{colorId}", method = RequestMethod.POST)
    public void Post(@PathVariable int id, @PathVariable String color) {
        Contour c = contourDao.findById(id);

        c.setROIcolor(color);

        contourDao.save(c);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void Put(@RequestBody Contour contour, @RequestParam int structureId, @RequestParam int sliceId) {

        Structure s = structureDAO.findById(structureId);
        Slice sl = sliceDAO.findById(sliceId);
        contour.setStructure(s);
        contour.setSlice(sl);
        contourDao.save(contour);
    }
}
