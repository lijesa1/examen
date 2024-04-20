package com.codigo.LilianaSalazar.controller;

import com.codigo.LilianaSalazar.entity.Empresa;
import com.codigo.LilianaSalazar.request.EmpresaRequest;
import com.codigo.LilianaSalazar.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empresa")
@AllArgsConstructor
@Tag(
        name = "Api de Empresas",
        description = "Esta api permite registrar una empresa"
)
public class EmpresaController {
    private final EmpresaService empresaService;

    @PostMapping("/crearEmpresa")
    @Operation(
            summary = "Registrar una empresa en base de datos",
            description = "Para usar endPoint debes enviar una objeto empresa, lo cual se va guardar en Base de datos previa validacion",
            parameters = {
                    @Parameter(name = "filtro", description = "Filtro de empresas")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS 200 significa registro exitoso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTTP STATUS 201 significa que se creó  de manera exitosa",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS 400 Bad Request",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<Empresa> crearEmpresa(@Valid @RequestBody EmpresaRequest request) {
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setRazonSocial(request.getRazonsocial());
        nuevaEmpresa.setTipoDocumento(request.getTipoDocumento());
        nuevaEmpresa.setNumeroDocumento(request.getNumeroDocumento());
        nuevaEmpresa.setCondicion(request.getCondicion());
        nuevaEmpresa.setDireccion(request.getDireccion());
        nuevaEmpresa.setDistrito(request.getDistrito());
        nuevaEmpresa.setProvincia(request.getProvincia());
        nuevaEmpresa.setDepartamento(request.getDepartamento());
        nuevaEmpresa.setEsAgenteRetencion(request.isEsAgenteRetencion());
        nuevaEmpresa.setEstado(request.getEstado());
        nuevaEmpresa.setUsuaCrea("lsalazar");
        nuevaEmpresa.setDate_create(getTimeStamp());
        nuevaEmpresa.setUsuaModif("lsalazar");
        nuevaEmpresa.setDateModif(getTimeStamp());
        nuevaEmpresa.setUsuaDelet("lsalazar");
        nuevaEmpresa.setDateDelet(getTimeStamp());
        Empresa empresaGuardada = empresaService.registrarEmpresa(nuevaEmpresa);
        return ResponseEntity.ok(empresaGuardada);
    }
    private Timestamp getTimeStamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
    @DeleteMapping("/eliminarEmpresa/{id}")
    @Operation(
            summary = "Convierte una empresa a estado DESACTIVADO",
            description = "Para usar endPoint debes enviar el id de la empresa que deseas eliminar",
            parameters = {
                    @Parameter(name = "filtro", description = "Filtro de empresas")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS 200 significa registro exitoso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTTP STATUS 201 significa que se creó  de manera exitosa",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS 400 Bad Request",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<Empresa> eliminarEmpresa(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.eliminarEmpresa(id));
    }

    @PutMapping("/actualizarEmpresa/{id}")
    @Operation(
            summary = "Actualizar los datos de una empresa registrada",
            description = "Para usar endPoint debes enviar un body de Empresa, lo cual actualizará todos sus atributos",
            parameters = {
                    @Parameter(name = "filtro", description = "Filtro de empresas")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS 200 significa registro exitoso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTTP STATUS 201 significa que se creó  de manera exitosa",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS 400 Bad Request",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<Empresa> actualizarEmpresa(@RequestBody EmpresaRequest request, @PathVariable Long id) {
        Empresa empresa = empresaService.buscarEmpresa(id);
        empresa.setRazonSocial(request.getRazonsocial());
        empresa.setTipoDocumento(request.getTipoDocumento());
        empresa.setNumeroDocumento(request.getNumeroDocumento());
        empresa.setCondicion(request.getCondicion());
        empresa.setDireccion(request.getDireccion());
        empresa.setDistrito(request.getDistrito());
        empresa.setProvincia(request.getProvincia());
        empresa.setDepartamento(request.getDepartamento());
        empresa.setEsAgenteRetencion(request.isEsAgenteRetencion());
        empresa.setEstado(request.getEstado());
        empresa.setUsuaModif("lsalazar");
        empresa.setDateModif(getTimeStamp());
        return ResponseEntity.ok(empresaService.registrarEmpresa(empresa));
    }


    @GetMapping("/{id}")


    @Operation(
            summary = "Actualizar los datos de una empresa registrada",
            description = "Para usar endPoint debes enviar un body de Empresa, lo cual actualizará todos sus atributos",
            parameters = {
                    @Parameter(name = "filtro", description = "Filtro de empresas")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS 200 significa registro exitoso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTTP STATUS 201 significa que se creó  de manera exitosa",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS 400 Bad Request",
                    content = @Content(mediaType = "application/json")
            )
    })


    public ResponseEntity<Optional<Empresa>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @GetMapping("/todos")

    @Operation(
            summary = "Actualizar los datos de una empresa registrada",
            description = "Para usar endPoint debes enviar un body de Empresa, lo cual actualizará todos sus atributos",
            parameters = {
                    @Parameter(name = "filtro", description = "Filtro de empresas")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS 200 significa registro exitoso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTTP STATUS 201 significa que se creó  de manera exitosa",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS 400 Bad Request",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<List<Empresa>> buscarTodos() {
        return ResponseEntity.ok(empresaService.buscarEmpresas());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}