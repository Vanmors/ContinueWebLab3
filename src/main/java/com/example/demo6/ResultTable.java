package com.example.demo6;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "dots")
public class ResultTable implements Serializable {

        //    @GenericGenerator(name="seq" , strategy="increment")
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "x")
//        @NotNull
        private Double x;

        @Column(name = "y")
//        @NotNull
        private Double y;

        @Column(name = "r")
//        @NotNull
        private Double r;

//        @NotNull
        @Column(name = "hit")
        private boolean hit;


    public ResultTable(Double x, Double y, Double r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }

//        public Boolean getHit() {
//            return hit;
//        }
//
//        public void setHit(Boolean hit) {
//            this.hit = hit;
//        }
//
//        public Double getR() {
//            return r;
//        }
//
//        public void setR(Double r) {
//            this.r = r;
//        }
//
//        public Double getY() {
//            return y;
//        }
//
//        public void setY(Double y) {
//            this.y = y;
//        }
//
//        public Double getX() {
//            return x;
//        }
//
//        public void setX(Double x) {
//            this.x = x;
//        }
    }
