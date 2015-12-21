package org.sphic.Controller;

import org.sphic.Model.Contour;
import org.sphic.Model.Slice;
import org.sphic.Model.Structure;
import org.sphic.Service.DAO.ContourDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contour")
public class ContourController {
    private ContourDao contourDao;

    @Autowired
    public ContourController(ContourDao contourDao) {
        this.contourDao = contourDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Contour Get(@PathVariable int id) {
        Contour contour = contourDao.get(Contour.class, id);
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
    public void Post(@PathVariable int id, @PathVariable int colorId) {
        Contour c = contourDao.get(Contour.class, id);

        c.setColorId(colorId);

        contourDao.save(c);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public int Put(@RequestBody Contour contour, @RequestParam int structureId, @RequestParam int sliceId) {

        Structure s = contourDao.get(Structure.class, structureId);
        Slice sl = contourDao.get(Slice.class, sliceId);
        contour.setStructure(s);
        contour.setSlice(sl);
        return contourDao.save(contour);
    }
}
