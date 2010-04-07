task :site do
  temp = "../temp"
  puts `git clone -l -s -b gh-pages . #{temp}`
  if system("mvn -Dmaven.test.skip=true site-deploy ")
      puts `cd #{temp} && git add -A && git commit -m "deploying site" && git push origin gh-pages`
      puts `git push origin gh-pages`
      puts " site deployed successfully "
 else
   puts " site could not be deployed "
 end
  puts `rm -r #{temp}`
end
