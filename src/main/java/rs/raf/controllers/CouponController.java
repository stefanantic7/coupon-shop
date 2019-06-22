package rs.raf.controllers;

import rs.raf.annotations.AuthenticatedAsAdmin;
import rs.raf.dtos.CouponDto;
import rs.raf.enums.CouponStatus;
import rs.raf.mappers.CouponMapperImpl;
import rs.raf.mappers.ShopMapperImpl;
import rs.raf.models.Coupon;
import rs.raf.models.Shop;
import rs.raf.requests.CreateCouponRequest;
import rs.raf.responses.PaginationResponse;
import rs.raf.services.CouponService;
import rs.raf.services.ShopService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/coupons")
public class CouponController {

    private CouponService couponService;

    @Inject
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginationResponse<CouponDto> get(@QueryParam("page")@DefaultValue("1") int page,
                                             @QueryParam("limit")@DefaultValue("10") int limit,
                                             @QueryParam("status")@DefaultValue("ALL") String couponStatus) {
        return this.couponService.paginate(limit, page, CouponStatus.valueOf(couponStatus.toUpperCase()));
    }

    @DELETE
    @Path("/{couponId}")
    @AuthenticatedAsAdmin
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("couponId") int couponId) {
        this.couponService.delete(couponId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @AuthenticatedAsAdmin
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CouponDto create(@Valid CreateCouponRequest createCouponRequest) {
        return this.couponService.create(createCouponRequest.getShopId(), createCouponRequest.getProduct(),
                createCouponRequest.getDiscountedPrice(), createCouponRequest.getOriginalPrice(),
                createCouponRequest.getValidFromDate(), createCouponRequest.getValidToDate());

    }

}
