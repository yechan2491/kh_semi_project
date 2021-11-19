/**
 * 
 */

$('#summernote2').summernote({
	lang: "ko-KR",					
	placeholder: '내용을 입력해주세요',
    tabsize: 5,
    height: 700,
    toolbar: [
    ['style', ['style']],
    ['font', ['bold', 'italic', 'underline', 'clear']],
    // ['font', ['bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear']],
    //['fontname', ['fontname']],
   // ['fontsize', ['fontsize']],
    ['color', ['color']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['height', ['height']],
    ['table', ['table']],
    ['insert', ['link', 'picture', 'hr']],
    //['view', ['fullscreen', 'codeview']],
    ['help', ['help']]
  ],
  });
