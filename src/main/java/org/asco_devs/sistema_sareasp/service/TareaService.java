package org.asco_devs.sistema_sareasp.service;

import org.asco_devs.sistema_sareasp.entity.Tareas;
import org.asco_devs.sistema_sareasp.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private TareasRepository tareasRepository;

    @Override
    public List<Tareas> listarTareas() {
        return tareasRepository.findAll();
    }

    @Override
    public Tareas buscarTareas(Integer idTarea) {
        Optional<Tareas> tarea = tareasRepository.findById(idTarea);
        return tarea.orElse(null);
    }

    @Override
    public void guardarTareas(Tareas tareas) {
        tareasRepository.save(tareas);
    }

    @Override
    public void eliminarTareas(Tareas tareas) {
        tareasRepository.delete(tareas);
    }
}