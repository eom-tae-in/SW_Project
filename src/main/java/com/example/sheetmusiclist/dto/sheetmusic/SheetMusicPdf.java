
package com.example.sheetmusiclist.dto.sheetmusic;


import com.example.sheetmusiclist.entity.pdf.Pdf;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SheetMusicPdf {
    @ApiModelProperty(notes = "악보 고유 번호", example = "1")
    private int pdfId;

    @ApiModelProperty(notes = "악보 변환 이름", example = "123213.jpg")
    private String uniqueName;

    public static SheetMusicPdf toDto(Pdf pdf){
        return new SheetMusicPdf(pdf.getId(),pdf.getUniqueName());
    }
}