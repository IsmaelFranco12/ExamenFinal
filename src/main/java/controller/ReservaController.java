package com.example.ExamenFinal.controller;
import com.example.ExamenFinal.repository.ReservaRepository;
import com.example.ExamenFinal.model.Reserva;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @PutMapping("/{id}")
    public Reserva actualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaDetalles) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow();
        reserva.setFechaInicio(reservaDetalles.getFechaInicio());
        reserva.setFechaFin(reservaDetalles.getFechaFin());
        return reservaRepository.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void cancelarReserva(@PathVariable Long id) {
        reservaRepository.deleteById(id);
    }
}
