package com.codigo.LilianaSalazar.service;
import com.codigo.LilianaSalazar.dao.EmpresaRepository;
import com.codigo.LilianaSalazar.entity.Empresa;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    @Transactional
    public Empresa registrarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa eliminarEmpresa(Long id) {
        Optional<Empresa> empresaRecuperada = empresaRepository.findById(id);
        if (empresaRecuperada.isPresent()) {
            empresaRecuperada.get().setEstado(0);
            empresaRecuperada.get().setUsuaDelet("jamoroto");
            empresaRecuperada.get().setDateDelet(getTimeStamp());
            return empresaRepository.save(empresaRecuperada.get());
        }
        return null;
    }

    private Timestamp getTimeStamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    public Empresa buscarEmpresa(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }


    public List<Empresa> buscarEmpresas(){
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id){
        return empresaRepository.findById(id);
    }

}