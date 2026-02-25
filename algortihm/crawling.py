import asyncio
import gspread
from oauth2client.service_account import ServiceAccountCredentials
from playwright.async_api import async_playwright


def setup_sheet():
    scope = ["https://spreadsheets.google.com/feeds", "https://www.googleapis.com/auth/drive"]
    creds = ServiceAccountCredentials.from_json_keyfile_name("credentials.json", scope)
    client = gspread.authorize(creds)
    # 시트 이름을 확인해 주세요
    return client.open("포르쉐_카이엔_리스트").get_worksheet(0)


async def main():
    sheet = setup_sheet()

    async with async_playwright() as p:
        browser = await p.chromium.launch(headless=False)  # M4 Pro의 속도를 눈으로 확인하세요!
        context = await browser.new_context(
            user_agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36"
        )
        page = await context.new_page()

        # 사용자님이 필터링한 결과 리스트 URL
        list_url = "https://www.encar.com/fc/fc_carsearchlist.do?carType=for#!%7B%22action%22%3A%22(And.Hidden.N._.(C.CarType.N._.(C.Manufacturer.%ED%8F%AC%EB%A5%B4%EC%89%90._.(C.ModelGroup.%EC%B9%B4%EC%9D%B4%EC%97%94._.(C.Model.%EC%B9%B4%EC%9D%B4%EC%97%94%20(PO536_)._.(Or.(C.BadgeGroup.%EA%B0%80%EC%86%94%EB%A6%B0%203000cc._.(Or.Badge.3_.0._.Badge.3_.0%20%EC%BF%A0%ED%8E%98.))_.(C.BadgeGroup.%EA%B0%80%EC%86%94%EB%A6%B0%2B%EC%A0%84%EA%B8%B0%203000cc._.(Or.Badge.3_.0%20E-%ED%95%98%EC%9D%B4%EB%B8%8C%EB%A6%AC%EB%93%9C._.Badge.3_.0%20E-%ED%95%98%EC%9D%B4%EB%B8%8C%EB%A6%AC%EB%93%9C%20%EC%BF%A0%ED%8E%98.)))))))_.Year.range(202400..)._.Mileage.range(..20000).)%22%2C%22toggle%22%3A%7B%7D%2C%22layer%22%3A%22%22%2C%22sort%22%3A%22Year%22%2C%22page%22%3A1%2C%22limit%22%3A%2240%22%2C%22searchKey%22%3A%22%22%2C%22loginCheck%22%3Afalse%7D"

        await page.goto(list_url, wait_until="domcontentloaded", timeout=60000)
        # 우측 박스가 로딩될 때까지 잠시 대기
        await page.wait_for_timeout(2000)

        car_items = []  # 미리 빈 리스트로 선언
        try:
            print("매물 데이터 로딩 완료!")
            # 1. 리스트가 나타날 때까지 대기 (ID인 #sr_photo 사용)
            await page.wait_for_selector("#sr_photo", timeout=30000)

            # 2. 리스트 안의 개별 차량(li)들 가져오기
            car_items = await page.locator("#sr_photo li").all()
            print(f"매물 데이터 로딩 완료! ({len(car_items)}개 발견)")

            # --- 첫 번째 항목 HTML 출력 (셀렉터 확인용) ---
            if car_items:
                first_html = await car_items[0].inner_html()
                print("=== 첫 번째 항목 HTML (셀렉터 확인용) ===")
                print(first_html[:2000])
                print("==========================================")


            # --- 여기서부터 데이터 추출 로직 시작 ---
            for item in car_items:
                try:
                    name_loc = item.locator(".cls")
                    price_loc = item.locator(".prc")

                    name = await name_loc.inner_text() if await name_loc.count() > 0 else "N/A"
                    price = await price_loc.inner_text() if await price_loc.count() > 0 else "N/A"

                    sheet.append_row([name.strip(), price.strip()])
                    print(f"저장: {name.strip()} / {price.strip()}")
                except Exception as item_e:
                    print(f"항목 추출 중 에러(무시하고 다음 항목으로): {item_e}")
                    continue

        except Exception as e:
            print(f"에러 발생: {e}")
            print("데이터가 로딩되지 않았습니다. 스크린샷을 찍습니다.")
            await page.screenshot(path="blank_page_debug.png")

        await browser.close()

if __name__ == "__main__":
    asyncio.run(main())
