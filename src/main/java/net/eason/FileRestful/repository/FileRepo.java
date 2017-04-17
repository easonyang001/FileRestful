package net.eason.FileRestful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.eason.FileRestful.entity.FileMetadata;

@Repository
public interface FileRepo extends JpaRepository<FileMetadata,Long>{

    @Query("select d from FileMetadata d where d.fileName like %:keyword% or d.userId like %:keyword%")
    List<FileMetadata> findByFileNameOrUserId(@Param("keyword") String keyword);

    List<FileMetadata> findByFileNameIgnoreCaseContaining(String keyword);
}
