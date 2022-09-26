package com.example.sheetmusiclist.repository.pdf;

import com.example.sheetmusiclist.entity.pdf.Pdf;
import com.example.sheetmusiclist.entity.sheetmusic.SheetMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdfRepository extends JpaRepository<Pdf, Long> {

    List<Pdf> findAllBySheetMusic(SheetMusic sheetMusic);
}
