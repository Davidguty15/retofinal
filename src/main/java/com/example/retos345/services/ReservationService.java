package com.example.retos345.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retos345.entities.Reservation;
import com.example.retos345.repositories.ReservationRepository;


@Service
public class ReservationService {
    
        @Autowired
        private ReservationRepository reservationRepository;

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

        public JSONObject getReservationsStatus(){
            List<Reservation> completed = this.reservationRepository.findByStatus("completed");
            List<Reservation> cancelled = this.reservationRepository.findByStatus("cancelled");
            JSONObject json = new JSONObject();
            json.put("completed", completed.size());
            json.put("cancelled", cancelled.size());
            return json;
        }

        public List<Reservation> getReservationsClients(){
            return this.reservationRepository.findAll();
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
