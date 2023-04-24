package com.proyecto.proyecto.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyecto.model.Reserva;
import com.proyecto.proyecto.model.Cliente;
import com.proyecto.proyecto.model.DTO.ReservaDTO;
import com.proyecto.proyecto.repository.ClienteRepository;
import com.proyecto.proyecto.repository.ProductoRepository;
import com.proyecto.proyecto.repository.UsersRepository;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.repository.ReservaRepository;
import com.proyecto.proyecto.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ObjectMapper mapper;
    private final UsersRepository userRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final EmailSenderServiceImpl emailSenderService;
    private static final Logger LOGGER = Logger.getLogger(ReservaServiceImpl.class);
    @Override
    public ReservaDTO agregarReserva(ReservaDTO reservaDTO){
        LOGGER.info("Se inicio una operacion de guardado de la Reserva a la hora: " + reservaDTO.getHoraReserva());
        Reserva reserva = Reserva.builder()
                .horaReserva(timeNow())
                .fechaInicio(reservaDTO.getFechaInicio())
                .fechaFinal(reservaDTO.getFechaFinal())
                .producto(reservaDTO.getProducto())
                .user(reservaDTO.getUser())

                .build();
        var reservaSaved = reservaRepository.save(reserva);
        var user = userRepository.findById(reservaDTO.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID:" + reservaDTO.getUser().getId() + " no existe"));
        var newClient = clienteRepository.findByEmail(user.getEmail());
        if (newClient.isEmpty()){
            Cliente cliente = Cliente.builder()
                    .first_name(user.getFirst_name())
                    .last_name(user.getLast_name())
                    .email(user.getEmail())
                    .fechaCliente(dateNow())
                    .build();
            clienteRepository.save(cliente);
        }
        var tituloReserva = productoRepository.findById(reservaDTO.getProducto().getId());
        emailSenderService.sendEmail(
                user.getEmail()
                ,"Reserva creada exitosamente!!"
                ,user.getFirst_name() + " " + user.getLast_name() +
                        " Digital Booking te felicita por haber reservado en " + tituloReserva.get().getTitulo() + " " + "de: " +
                        reservaDTO.getFechaInicio() + " a " + reservaDTO.getFechaFinal());
        return mapper.convertValue(reservaSaved, ReservaDTO.class);
    }
    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO){
        LOGGER.info("Se inicio una operacion de actualizado de reserva con ID= " + reservaDTO.getId());
        reservaRepository.findById(reservaDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("La reserva con ID: " + reservaDTO.getId() + " no existe"));
        return mapper.convertValue(reservaRepository.save(mapper.convertValue(reservaDTO, Reserva.class)), ReservaDTO.class);
    }
    @Override
    public ReservaDTO buscarReserva(Long id) {
        LOGGER.info("Se inicio una operacion de busqueda de reserva con ID " + id);
        return reservaRepository.findById(id)
                .map(city -> mapper.convertValue(city, ReservaDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("La reserva con ID: " + id + " no existe"));
    }
    @Override
    public List<ReservaDTO> listaReserva() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de reservas ");
        var list = reservaRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de reservas se encuentra vacia");
        }
        return list.stream().map(city -> mapper.convertValue(city, ReservaDTO.class)).collect(Collectors.toList());
    }
    @Override
    public void eliminarReserva(Long id){
        reservaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La reserva con ID: " + id + " no existe"));
        reservaRepository.deleteById(id);
    }

    @Override
    public List<ReservaDTO> findAllByProductoId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de reservas x producto Id: " + id);
        var list = reservaRepository.findAllByProductoId(id);
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de reservas del producto ID: " + id + "se encuentra vacia");
        }
        return list.stream().map(reserva -> mapper.convertValue(reserva, ReservaDTO.class)).collect(Collectors.toList());
    }
    @Override
    public List<ReservaDTO> findAllByUserId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de reservas x user Id: " + id);
        var list = reservaRepository.findAllByUserId(id);
        if (list.isEmpty()){
            throw new ResourceNotFoundException("La lista de reservas del User ID: " + id + "se encuentra vacia");
        }
        return list.stream().map(reserva -> mapper.convertValue(reserva, ReservaDTO.class)).collect(Collectors.toList());
    }

    public String timeNow(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }
    public String dateNow(){
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateObj.format(formatter);
    }
}
