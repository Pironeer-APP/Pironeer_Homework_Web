document.addEventListener('DOMContentLoaded', () => {
    const isMobile = window.matchMedia('(max-width: 768px)').matches;

    if (!isMobile) {
        const cursor = document.createElement('div');
        cursor.classList.add('cursor');
        document.body.appendChild(cursor);

        const updateCursorPosition = (e) => {
            cursor.style.left = `${e.clientX}px`;
            cursor.style.top = `${e.clientY}px`;
        };

        document.addEventListener('mousemove', updateCursorPosition);

        // 화면 밖으로 나갈 때 동그라미 숨기기
        document.addEventListener('mouseleave', () => {
            cursor.style.opacity = '0';
        });

        document.addEventListener('mouseenter', () => {
            cursor.style.opacity = '1';
        });

        // 커스텀 커서를 유지하고 기본 커서를 완전히 제거
        document.querySelectorAll('input, textarea, button, select, a, label, div, p,h1, h2, h3,h4,h5,h6, span').forEach((el) => {
            el.style.cursor = 'none'; // 기본 커서를 제거
        });
    } else {
        console.log('모바일 환경에서는 커스텀 커서를 비활성화합니다.');
    }
});
