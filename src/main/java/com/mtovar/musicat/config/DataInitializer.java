package com.mtovar.musicat.config;

import com.mtovar.musicat.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final ArtistService artistService;

    @Autowired
    public DataInitializer(ArtistService artistService) {
        this.artistService = artistService;
    }

//    @PostConstruct
//    public void populate() {
//        this.artistService.init();
//    }

    //    @EventListener(ApplicationReadyEvent.class)
    @EventListener(ApplicationStartedEvent.class)
    public void populate() { //initialize sample data
        this.artistService.init();

//        Artist firstArtist = artistService.findById(1L);
//        Instrument firstInstrument = instrumentService.findById(1L);
//        firstArtist.setInstruments(Set.of(firstInstrument));
//        artistService.update(firstArtist.getId(), firstArtist);
    }
}

//Qué sería más correcto, @PostConstruct, @EventListener(ApplicationReadyEvent.class) o @EventListener(ApplicationStartedEvent.class)?
//
//@PostConstruct is called after dependency injection but before the application is fully started. It is not recommended for initializing data that depends on the full Spring context or external resources being ready.
//
//@EventListener(ApplicationStartedEvent.class) triggers when the application context has started but before the application is ready to service requests. Some beans or resources may not be fully initialized yet.
//
//@EventListener(ApplicationReadyEvent.class) is the most appropriate for data initialization that requires the application to be fully started and ready to handle requests. All beans are initialized, and the context is fully set up.
//
//Conclusion:
//For initializing data after the application is fully ready, use @EventListener(ApplicationReadyEvent.class). This is the most robust and professional choice for most Spring Boot applications.