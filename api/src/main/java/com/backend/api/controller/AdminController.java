package com.backend.api.controller;

import com.backend.api.entity.*;
import com.backend.api.request.*;
import com.backend.api.response.*;
import com.backend.api.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final PaginationService paginationService;
    private final AuthService authService;
    private final ImageService imageService;
    private final WriterService writerService;
    private final ArticleService articleService;
    private final EnquiryService enquiryService;
    private final OrganizerService organizerService;
    private final HotelService hotelService;
    private final PlaceService placeService;
    private final StaticService staticService;
    private final TourTypeService tourTypeService;
    private final TourService tourService;
    private final TourLogService tourLogService;
    private final PackageService packageService;

//    Images API
    @Tag(name = "Upload Image")
    @PostMapping(value = "/images",  consumes = {"multipart/form-data"})
    public ImageEntity uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) throws Exception {
        authService.verifyAdminToken();
        try {
            if (file.isEmpty()) {
                throw new Exception("Please select a file to upload");
            }

            byte[] imageData = file.getBytes();

            return imageService.uploadImage(imageData, title);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Tag(name = "Get Images List")
    @GetMapping("/images")
    public PaginatedRes getImages(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<ImageEntity> imageEntities = imageService.getImages();
        Page<ImageEntity> pageInfo = (Page<ImageEntity>) imageEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Delete Image")
    @DeleteMapping("/images/{id}")
    public void deleteImage(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        imageService.deleteImage(id);
    }

//    Writers API
    @Tag(name = "Create Writer")
    @PostMapping("/writers")
    public void createWriter(@Valid @RequestBody WriterCreateReq writerCreateReq) throws Exception {
        authService.verifyAdminToken();
        writerService.createWriter(writerCreateReq);
    }

    @Tag(name = "Get Writers List")
    @GetMapping("/writers")
    public PaginatedRes getWriters(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<WriterEntity> writerEntities = writerService.getWriters(name, email);
        Page<WriterEntity> pageInfo = (Page<WriterEntity>) writerEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Update Writer")
    @PutMapping("/writers")
    public void updateWriter(@Valid @RequestBody WriterUpdateReq writerUpdateReq) throws Exception {
        authService.verifyAdminToken();
        writerService.updateWriter(writerUpdateReq);
    }

    @Tag(name = "Get Writer By ID")
    @GetMapping("/writers/{id}")
    public WriterEntity getWriterById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return writerService.getWriterById(id);
    }

    @Tag(name = "Delete Writer By ID")
    @DeleteMapping("/writers/{id}")
    public void deleteWriterById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        writerService.deleteWriterById(id);
    }

//    Article API
    @Tag(name = "Create Article")
    @PostMapping("/articles")
    public void createArticle(@Valid @RequestBody ArticleCreateReq articleCreateReq) throws Exception {
        authService.verifyAdminToken();
        articleService.createArticle(articleCreateReq);
    }

    @Tag(name = "Get Articles List")
    @GetMapping("/articles")
    public PaginatedRes getArticles(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<ArticleEntity> articleEntities = articleService.getArticles();
        Page<ArticleEntity> pageInfo = (Page<ArticleEntity>) articleEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Update Article")
    @PutMapping("/articles")
    public void updateArticle(@Valid @RequestBody ArticleUpdateReq articleUpdateReq) throws Exception {
        authService.verifyAdminToken();
        articleService.updateArticle(articleUpdateReq);
    }

    @Tag(name = "Get Article By ID")
    @GetMapping("/articles/{id}")
    public ArticleEntity getArticleById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return articleService.getArticleById(id);
    }

    @Tag(name = "Delete Article By ID")
    @DeleteMapping("/articles/{id}")
    public void deleteArticleById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        articleService.deleteArticleById(id);
    }

//    Enquiry API
    @Tag(name = "Answer Enquiry")
    @PutMapping("/enquiries")
    public void answerQuestion(@Valid @RequestBody EnquiryAnswerReq enquiryAnswerReq) throws Exception {
        authService.verifyAdminToken();
        enquiryService.answerQuestion(enquiryAnswerReq);
    }

    @Tag(name = "Get Enquiry List")
    @GetMapping("/enquiries")
    public PaginatedRes getEnquiries(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<GroupedEnquiryRes> groupedEnquiryRes = enquiryService.getGroupedEnquiries();
        Page<GroupedEnquiryRes> pageInfo = (Page<GroupedEnquiryRes>) groupedEnquiryRes;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

//    Organizer API
    @Tag(name = "Create Organizer")
    @PostMapping("/organizers")
    public void createOrganizer(@Valid @RequestBody OrganizerCreateReq organizerCreateReq) throws Exception {
        authService.verifyAdminToken();
        organizerService.createOrganizer(organizerCreateReq);
    }

    @Tag(name = "Get Organizer List")
    @GetMapping("/organizers")
    public PaginatedRes getOrganizers(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<OrganizerEntity> organizerEntities = organizerService.getOrganizers(name, email);
        Page<OrganizerEntity> pageInfo = (Page<OrganizerEntity>) organizerEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Update Organizer")
    @PutMapping("/organizers")
    public void updateOrganizer(@Valid @RequestBody OrganizerUpdateReq organizerUpdateReq) throws Exception {
        authService.verifyAdminToken();
        organizerService.updateOrganizer(organizerUpdateReq);
    }

    @Tag(name = "Get Organizer By ID")
    @GetMapping("/organizers/{id}")
    public OrganizerEntity getOrganizerById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return organizerService.getOrganizerById(id);
    }

    @Tag(name = "Delete Organizer By ID")
    @DeleteMapping("/organizers/{id}")
    public void deleteOrganizerById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        organizerService.deleteOrganizerById(id);
    }

//    Places

    @Tag(name = "Create Place")
    @PostMapping("/places")
    public void createPlace(@Valid @RequestBody PlaceCreateReq placeCreateReq) throws Exception {
        authService.verifyAdminToken();
        placeService.createPlace(placeCreateReq);
    }

    @Tag(name = "Get Place List")
    @GetMapping("/places")
    public PaginatedRes getPlaces(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<PlaceEntity> placeEntities = placeService.getPlaces(name);
        Page<PlaceEntity> pageInfo = (Page<PlaceEntity>) placeEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Get Place Types")
    @GetMapping("/place-types")
    public List<PlaceTypeRes> getPlaceTypes() throws Exception {
        authService.verifyAdminToken();
        return placeService.getPlaceTypes();
    }

    @Tag(name = "Update Place")
    @PutMapping("/places")
    public void updatePlace(@Valid @RequestBody PlaceUpdateReq placeUpdateReq) throws Exception {
        authService.verifyAdminToken();
        placeService.updatePlace(placeUpdateReq);
    }

    @Tag(name = "Get Place By ID")
    @GetMapping("/places/{id}")
    public PlaceEntity getPlaceById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return placeService.getPlaceById(id);
    }

    @Tag(name = "Delete Place By ID")
    @DeleteMapping("/places/{id}")
    public void deletePlaceById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        placeService.deletePlaceById(id);
    }

//    Divisions

    @Tag(name = "Get Divisions")
    @GetMapping("/divisions")
    public List<DivisionWithDistrictsRes> getDivisionsWithDistricts() throws Exception {
        authService.verifyAdminToken();
        return staticService.getDivisionsWithDistricts();
    }

//    Districts

    @Tag(name = "Get Districts")
    @GetMapping("/districts")
    public List<DistrictEntity> getDistricts() throws Exception {
        authService.verifyAdminToken();
        return staticService.getDistricts();
    }

//    Hotels

    @Tag(name = "Create Hotel")
    @PostMapping("/hotels")
    public void createHotel(@Valid @RequestBody HotelCreateReq hotelCreateReq) throws Exception {
        authService.verifyAdminToken();
        hotelService.createHotel(hotelCreateReq);
    }

    @Tag(name = "Get Hotel List")
    @GetMapping("/hotels")
    public PaginatedRes getHotels(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<HotelEntity> hotelEntities = hotelService.getHotels(name);
        Page<HotelEntity> pageInfo = (Page<HotelEntity>) hotelEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Update Hotel")
    @PutMapping("/hotels")
    public void updateHotel(@Valid @RequestBody HotelUpdateReq hotelUpdateReq) throws Exception {
        authService.verifyAdminToken();
        hotelService.updateHotel(hotelUpdateReq);
    }

    @Tag(name = "Get Hotel By ID")
    @GetMapping("/hotels/{id}")
    public HotelEntity getHotelById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return hotelService.getHotelById(id);
    }

    @Tag(name = "Delete Hotel By ID")
    @DeleteMapping("/hotels/{id}")
    public void deleteHotelById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        hotelService.deleteHotelById(id);
    }

//    Tour Types

    @Tag(name = "Create Tour Type")
    @PostMapping("/tour-types")
    public void createTourType(@Valid @RequestBody TourTypeCreateReq tourTypeCreateReq) throws Exception {
        authService.verifyAdminToken();
        tourTypeService.createTourType(tourTypeCreateReq);
    }

    @Tag(name = "Get Tour Type List")
    @GetMapping("/tour-types")
    public List<TourTypeEntity> getTourTypes() throws Exception {
        authService.verifyAdminToken();
        return tourTypeService.getTourTypes();
    }

    @Tag(name = "Update Tour Type")
    @PutMapping("/tour-types")
    public void updateTourType(@Valid @RequestBody TourTypeUpdateReq tourTypeUpdateReq) throws Exception {
        authService.verifyAdminToken();
        tourTypeService.updateTourType(tourTypeUpdateReq);
    }

    @Tag(name = "Get Tour Type By ID")
    @GetMapping("/tour-types/{id}")
    public TourTypeEntity getTourTypeById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return tourTypeService.getTourTypeById(id);
    }

    @Tag(name = "Delete Tour Type By ID")
    @DeleteMapping("/tour-types/{id}")
    public void deleteTourTypeById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        tourTypeService.deleteTourTypeById(id);
    }

//    Tours
    @Tag(name = "Create Tour")
    @PostMapping("/tours")
    public void createTour(@Valid @RequestBody TourCreateReq tourCreateReq) throws Exception {
        authService.verifyAdminToken();
        tourService.createTour(tourCreateReq);
    }

    @Tag(name = "Get Tour List")
    @GetMapping("/tours")
    public PaginatedRes getTours(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<TourListRes> tourEntities = tourService.getTours();
        Page<TourListRes> pageInfo = (Page<TourListRes>) tourEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Get Tour By ID")
    @GetMapping("/tours/{id}")
    public TourEntity getTourById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return tourService.getTourById(id);
    }

//    Tour Logs
    @Tag(name = "Create Tour Log")
    @PostMapping("/tour-logs")
    public TourLogEntity createTourLog(@Valid @RequestBody TourLogCreateReq tourLogCreateReq) throws Exception {
        authService.verifyAdminToken();
        tourLogService.createTourLog(tourLogCreateReq);
        return tourLogService.getTourLogByTourIdAndDate(tourLogCreateReq.getToursId(), tourLogCreateReq.getDate());
    }

//    Packages
    @Tag(name = "Create Tour Package")
    @PostMapping("/tour-packages")
    public PackageEntity createPackage(@Valid @RequestBody TourPackageCreateReq tourPackageCreateReq) throws Exception {
        authService.verifyAdminToken();
        packageService.createPackage(tourPackageCreateReq);
        return packageService.getPackageByTourIdAndTitle(tourPackageCreateReq.getToursId(), tourPackageCreateReq.getTitle());
    }

}
