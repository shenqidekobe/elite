var gulp = require('gulp');  

var compass = require( 'gulp-compass' );

var browserSync = require("browser-sync");

//压缩js代码
var uglify = require('gulp-uglify');

var reload  = browserSync.reload;

gulp.task('js', function() {
    gulp.src('./script/myjs/*.js')
        .pipe(uglify())
        .pipe(gulp.dest('./script/js'));
});

gulp.task('compass', function() {
  gulp.src('./style/sass/*.scss').pipe(compass({
      config_file: './style/config.rb',
      css: './style/css',
      sass: './style/sass'
    })).pipe(reload({stream:true}));
});

gulp.task('default', ['compass', 'js'], function () {
  gulp.watch(['js'], ['compass']);
});