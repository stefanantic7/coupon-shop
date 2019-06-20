package rs.raf.controllers;

import rs.raf.models.Coupon;
import rs.raf.models.Shop;
import rs.raf.repositories.CouponRepository;
import rs.raf.services.ShopService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/coupons")
public class CouponController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coupon> get() {
        return null;
    }

}
