package org.asco_devs.sistema_sareasp.service;

import org.asco_devs.sistema_sareasp.entity.Tareas;
import java.util.List;

public interface ITareaService {
    public List<Tareas> listarTareas();
    public Tareas buscarTareas(Integer tareas);
    public void guardarTareas(Tareas tareas);
    public void eliminarTareas(Tareas tareas);

}
