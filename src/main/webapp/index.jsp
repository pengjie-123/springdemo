<html>
<body>
<h2>Hello World!</h2>

<script type="text/javascript">
    function init() {
        var html = "";
        var arr = ['1', '2', '3']
        html += arr.reduce(function (result, current) {
            return ((result || '') + (current + 'abc'));
        });
        return html;
    }
    alert(init());
</script>
</body>
</html>
