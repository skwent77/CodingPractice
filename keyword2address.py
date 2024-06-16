import fitz
# Load the PDF file
pdf_path = "./2번쨰 교본copy.pdf"
output_path = "./2번쨰 교본copy_highlighted.pdf"

# Open the PDF
pdf_document = fitz.open(pdf_path)

# Define highlights
highlights = {
    35: ["에밀 뒤르켐"],
    37: ["차별적 접촉"],
    39: ["하워드 베커"],
    41: ["공식적 통제"],
    43: ["청소년 범죄 증가"],
    45: ["범행의 조직화"],
    47: ["신뢰", "규범", "네트워크"],
    49: ["거래 비용 감소", "사회자본은 거래 비용을 줄이고 협력을 증진시켜 경제적 활동을 원활하게 하며, 경제 발전에 기여한다."],
    51: ["로버트 퍼트남", "사회자본은 시민들 간의 신뢰와 협력을 증진시켜, 민주주의가 더욱 공고해지고 효과적으로 작동하는 데 기여한다."],
    53: ["낮은 사회적 신뢰"],
    55: ["대량생산", "포디즘은 대량생산과 표준화된 제품을 강조하며"],
    57: ["유연한 생산", "포스트포디즘은 유연한 생산과 다양화를 강조한다."],
    59: ["노동자 통제", "노동통제이론은 노동자 통제를 통해 생산성과 효율성을 높이려는 노력이 노동 문제의 주요 원인이라고 설명한다."],
    61: ["고용 불안정"],
    63: ["기업의 인건비 절감 노력"],
    65: ["비정규직 노동자에 대한 법적 보호를 강화하여 고용의 안정성과 노동 조건을 개선해야 한다."]
}

# Highlight the specified text
for page_num, words in highlights.items():
    page = pdf_document[page_num - 1]
    for word in words:
        text_instances = page.search_for(word)
        for inst in text_instances:
            highlight = page.add_highlight_annot(inst)
            highlight.update()

# Save the highlighted PDF
pdf_document.save(output_path)
pdf_document.close()

output_path