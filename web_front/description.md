## DOM?

여러분이 지금 화면으로 보고 있는 것이 사실 HTML, CSS, JS가 아니라고 하면 믿으시겠습니까.
웹 페이지는 HTML 코드가 아닌, 그 코드로 인해 만들어진 **DOM** 이라는 친구이다.

**DOM(Document Object Model)** 은 웹 페이지의 구조를 정의하는 트리 형태의 모델이고, 
실제 보이는 웹페이지는 HTML과 CSS로 작성된 코드를 기반으로 만들어진 하나의 **DOM**이다 !

`그럼 우리가 보는건 HTML CSS인데 JS는 왜필요함?` 이라고 말한다면,
이 JavaScript라는 언어가 바로 DOM을 조작할 수 있게 해주는 친구다.

이거 없으면 걍 화면에 이미지 띄워놓은거나 다름 없다고 말할 수 있음.

## React를 왜 씀?

바닐라 JS를 구현해 봤다면 알겠지만, 이놈은 **직접 DOM을 조작**한다. 

예를 들어 사용자가 +1 버튼을 눌러서 카운트가 +1이 되는 페이지를 만들려면, 우리가 JS 코드를 쓸 때 `이 버튼은 이 페이지의 DOM에서 이 요소를 건드릴 거야!`라고 명시적으로 지시해야 하는데..

지금이야 뭐 버튼 하나, 이벤트 하나 정도라서 간단해 보일 수 있지만, 만약 웹 페이지가 굉장히 복잡해진다면?
`이 버튼은 이 페이지의 DOM에서 이 요소를 건드리고, 저 버튼은 저 페이지의 DOM에서 저 요소를 조작하고, 또 다른 버튼은...` 같은 작업이 반복되어 코드가 굉장히 복잡해지고 관리하기 어려워질 것이다.

바닐라 JS로 DOM에 접근하려면 개발자가 **직접 DOM 요소를 찾아서** 조작해야 하기 때문에, 어떤 요소를 건드려야 하는지 일일이 찾아야 한다. 이런 작업이 많아질수록 코드가 난잡해지고, 실수할 가능성도 커짐.

**React가 탄생한 이유**가 바로 여기에 있다.

> *보다 쉬운 DOM 조작*
직접 DOM을 수정하는 것보다 *빠른 렌더링*
> 

### 가상 DOM

다시 사용자가 +1 버튼을 눌렀다고 하자.
바닐라 JS가 우리 눈에 보이는 DOM을 직접 조작한다면, React는 일단 **가상 DOM**이라는 놈을 만든다.
가상 DOM은 이제 사용자가 +1 버튼을 누른 뒤의 상태에서 새로 그린 DOM, 쉽게는 **새로 그려본 웹페이지**이다.


그 다음,실제 DOM과 가상 DOM을 비교하고,
React는 둘의 차이점을 찾아서 해당 부분만 바꿔주며 **리렌더링** 하게 된다.

이 과정을 통해 전체 DOM을 다시 그리지 않고도 변경된 부분만 효율적으로 업데이트할 수 있고, 
DOM을 직접 조작하는 것보다 훨씬 **빠른 렌더링 속도**를 제공한다.

### JSX

React는 Javascript 확장 언어인 **JSX**를 사용한다.

HTML을 작성하듯 쓸 수 있는 JSX는, 실제로는 Javascript 코드로 변환되고, 이 과정에서 우리가 직접 DOM의 어떤 요소에 접근하는 지 지정하지 않아도 **자동으로 DOM 접근/조작을 처리**해준다. 즉, 개발자는 UI가 어떻게 보여야 하는지(HTML 설계)에만 집중할 수 있고, 복잡한 DOM 조작(`document.createElement`, `queryselector`, `addEventListener`)은 React가 알아서 처리해 주는 것!

### 컴포넌트

React에서는 모든 UI가 **컴포넌트**로 나뉘어져 있다. 

각 컴포넌트는 자체적으로 상태(state)와 프롭스(props)를 관리하며, 필요한 경우에만 리렌더링 된다.
개발자는 특정 DOM 요소를 직접 선택해 조작할 필요 없이, 컴포넌트를 업데이트함으로써 해당 UI에 연결된 실제 DOM 조작은 React가 처리했으니 안심하라고~

결국 React는 **복잡한 DOM 조작을 더 간편하게** 만들고, 웹 페이지의 **성능을 최적화**하면서도 **유지보수가 용이**한 코드를 작성할 수 있게 도와준다.
