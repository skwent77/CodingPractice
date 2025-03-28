Spring의 @Async를 이용한 비동기 처리 심화 학습
@Async 기본 개념 복습
@Async는 Spring에서 제공하는 비동기 처리 어노테이션

메서드에 적용하면 별도 스레드에서 실행되며 호출자는 결과를 기다리지 않음

@EnableAsync를 설정 클래스에 추가해야 기능 활성화

심화 내용
1. Executor 커스터마이징
기본적으로 SimpleAsyncTaskExecutor를 사용하지만, 커스텀 Executor를 정의할 수 있습니다.
