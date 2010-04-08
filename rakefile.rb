#
#     Copyright SocialSite (C) 2009
#
#     This program is free software: you can redistribute it and/or modify
#     it under the terms of the GNU General Public License as published by
#     the Free Software Foundation, either version 3 of the License, or
#     (at your option) any later version.
#
#     This program is distributed in the hope that it will be useful,
#     but WITHOUT ANY WARRANTY; without even the implied warranty of
#     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#     GNU General Public License for more details.
#
#     You should have received a copy of the GNU General Public License
#     along with this program.  If not, see <http://www.gnu.org/licenses/>.
#

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
