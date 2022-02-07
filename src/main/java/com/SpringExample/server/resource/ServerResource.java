package com.SpringExample.server.resource;

import com.SpringExample.server.enums.Status;
import com.SpringExample.server.model.Response;
import com.SpringExample.server.model.Server;
import com.SpringExample.server.service.ServerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static java.util.Map.*;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImp serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServer(){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now ()).
                data(of("servers",serverService.list(30)))
                .message("Server retriewed").
                status(HttpStatus.OK).
                statusCode(HttpStatus.OK.value()).build());
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server=serverService.ping(ipAddress);
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now ()).
                data(of("server",server))
                .message(server.getStatus()== Status.SERVER_UP?"Ping Success":"Ping Failed").
                status(HttpStatus.OK).
                statusCode(HttpStatus.OK.value()).build());
    }

    @PostMapping("/saveserver")
    public ResponseEntity<Response> saveServer(@RequestBody Server server) {

        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now ()).
                data(of("server",serverService.create(server)))
                .message("Server Created").
                status(HttpStatus.CREATED).
                statusCode(HttpStatus.CREATED.value()).build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now ()).
                data(of("server",serverService.get(id)))
                .message("Server Retreived").
                status(HttpStatus.OK).
                statusCode(HttpStatus.OK.value()).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now ()).
                data(of("deleted",serverService.delete(id)))
                .message("Server is deleted").
                status(HttpStatus.OK).
                statusCode(HttpStatus.OK.value()).build());
    }
}
