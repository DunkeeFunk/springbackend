package mike.springframework.springmvcrest.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@Table(name="customerData")
public class CustomerData {

    @Id
    @Column(name="data_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(name="dates")
    private List<LocalDate> dates;

    @ElementCollection
    @Column(name="data")
    private List<Double> data;

    public void setId(Long id){ this.id = id;}

    public void setDates( List<LocalDate> dates){
        this.dates = dates;
    }

    public List<LocalDate> getDates() {
        return this.dates;
    }

    public void setData(List<Double> data){ this.data = data; }

    public List<Double> getData(){
        return this.data;
    }

}

/*
    public void randNums(){
        List<Double> data = new ArrayList<Double>();
        Random rand = new Random();
        data = rand.doubles()
                .distinct()
                .map(d -> 1 + d * 9)
                .limit(100)
                .boxed()
                .collect(Collectors.toList());
        // assign
        this.data = data;
    }

    public void dates(){
        // example dates
        LocalDate startDate = LocalDate.of(2019, 07, 15);
        LocalDate endDate = LocalDate.of(2019, 10, 23);
        // get the limit between the two dates, this could be a lot cleaner
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        // return a list of the time stamps between these dates
        this.dates = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
    */
