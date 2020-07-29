package org.geektime.jpa.complex.demo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/29 11:46
 */
@NoRepositoryBean
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {

    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
