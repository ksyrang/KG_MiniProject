# KG_MiniProject
취업 활동을 위한 미니프로젝트를 진행

헬스장 통합 관리 프로그램 설계
- 주요 기능 설명 -

​

< 공통 >

1. ID 별 로그인 기능(성공/실패에 따른 알림 창 표시, 가입 승인 여부, 강사, 관리자 별 권한 차등)

2. Main Login 페이지와 각 User 별 Welcome 페이지에서의 로그 아웃 기능

3. 이전 기능(하위 페이지에서 상위 페이지로 이동)

​

< 회원 >

1. 회원 가입 기능(아이디, 전화번호 중복 여부, 필수 입력 항목, 전화번호 및 생년월일 형식 제안)

2. 아이디/비밀번호 찾기 기능

3. 수강 중인 회원권 및 프로그램 스케줄 전체 조회 기능(클릭 시 해당 회원권 및 프로그램 스케줄의 상세정보 표시)

4. 회원권 구매 기능(개월 선택 및 시작 날짜 선택 시 종료 날짜 자동 표시, 시작 날짜를 오늘 날짜 이전 선택 불가능)

5. 프로그램 스케줄 신청 기능(강사별로 생성한 프로그램 중 하나 선택, 정원 만원 여부에 따른 선택 불가능)

6. 결제 기능(결제 종류 및 결제 완료 후 결제 정보 DB에 업로드)

7. 회원 정보 수정 기능(회원 이름 클릭 시 가능, 본인의 정보 자동 업로드, ID 제외 회원정보 수정 및 삭제 가능)

​

< 강사 >

1. 본인이 운영 중인 프로그램 전체 조회 기능(클릭 시 상세정보 표시)

2. 프로그램 스케줄 개설 가능(관리자가 개설한 프로그램 종류 선택 후 개설)

3. 프로그램 스케줄  수정 기능(본인이 운영 중인 프로그램 스케줄의 세부 정보 수정 및 삭제 가능)

4. 강사 정보 수정 기능(강사 이름 클릭 시 가능, 본인의 정보 자동 업로드, ID 제외 회원정보 수정 및 삭제 가능)

​

< 관리자 >

1. 회원 관리 기능(전체 조회 및 세부 조회, 가입 승인 관리, 회원정보 수정 및 삭제 가능)

2. 강사 관리 기능(전체 조회 및 세부 조회, 강사 등록 및 정보 수정, 삭제 가능)

3. 프로그램 종류 관리(프로그램 생성, 수정, 삭제 가능, 중복 등록 시 경고 알림 창 표시)

4. 프로그램 스케줄 관리 기능(스케줄 선택 시 상세정보 업로드, 정보 수정 및 삭제 가능, 프로그램 정보 중복 시 

                           수정 불가능, 현 수강인원 보다 정원을 적게 수정 불가능, 기간 선택 시 오늘 날짜 이전 선택 불가능)

5. 회원권 관리 기능(회원권 생성, 조회, 수정 삭제 가능, 중복 등록(개월) 시 등록 불가능)

6. 매출 조회 기능(전체 매출, 회원권 전체 또는 개월별 조회, 프로그램 전체 또는 종류별 조회, 

                            강사별 조회, 프로그램 스케줄별 조회 가능)

7. 통계 프로그램 기능(성비 파이 그래프, 프로그램별 파이 그래프, 강사별 매출 바 그래프, 월별 폐출  바 그래프)

​

< 가정 사항 >

- 관리자 계정은 admin/1234로 지정

- 회원은 회원 가입을 통해, 강사는 관리자를 통해 등록한다.

- 수강 중인 회원이 존재하는 프로그램 스케줄 및 프로그램, 회원권은 삭제하지 못한다.
