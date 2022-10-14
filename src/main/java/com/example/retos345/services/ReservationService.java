package com.example.retos345.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retos345.entities.Client;
import com.example.retos345.entities.Reservation;
import com.example.retos345.repositories.ClientRepository;
import com.example.retos345.repositories.ReservationRepository;


@Service
public class ReservationService {
    
        @Autowired
        private ReservationRepository reservationRepository;

        @Autowired
        private ClientRepository clientRepository;

        public ReservationService(ReservationRepository reservationRepository) {
            this.reservationRepository = reservationRepository;
        }

        // ****** INICIO REPORTES ******
        public List<Reservation> getReservationsBetweenTime(String start, String end){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
            formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
            List<Reservation> result = null;
            try {
                Date startDate = formatter.parse(start);
                Date endDate = formatter.parse(end);
                result = this.reservationRepository.findByStartDateBetween(startDate, endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return result;
        }

        public String getReservationsStatus(){
            List<Reservation> completed = this.reservationRepository.findByStatus("completed");
            List<Reservation> cancelled = this.reservationRepository.findByStatus("cancelled");
            String result = "{"+"\"completed\":"+completed.size()+","
                            + "\"cancelled\":"+cancelled.size()
                            + "}";
            return result;
        }

        public List<Client> getReservationsClients(){
            return this.clientRepository.findAll();
        }


        // ****** FIN REPORTES ******

        // METODOS CRUD
        public List<Reservation> getListReservations(){
            return this.reservationRepository.findAll();
        }

        public Reservation getReservation(int id){
            if(!this.reservationRepository.findById(id).isEmpty()){
                return this.reservationRepository.findById(id).get();
            }else{
                return null;
            }
        }

        public Reservation crearReservation(Reservation reservation){
            return this.reservationRepository.save(reservation);
        }

        public void eliminarReservation(int id){
            if(!this.reservationRepository.findById(id).isEmpty()){
                this.reservationRepository.deleteById(id);
            }
        }

        public void actualizarReservation(int id, Reservation reservation){
            if(!this.reservationRepository.findById(id).isEmpty()){
                Reservation reservationDB = this.reservationRepository.findById(id).get();

                this.reservationRepository.save(reservationDB);
            }
        }
}
