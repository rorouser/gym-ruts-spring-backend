package com.mygymroutine.persistence.routine;

import java.util.List;

import com.mygymroutine.persistence.routine.routineCreation.RoutineCreation;
import com.mygymroutine.persistence.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Routine {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long routineId;

    private String routineName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "routine", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<RoutineCreation> routineCreation;
    
    public Routine( User user, String routineName) {
        this.routineName = routineName;
        this.user = user;
    }

}

