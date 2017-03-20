cmake_minimum_required( VERSION 2.8 )
project( csc232 )
set( CMAKE_INSTALL_PREFIX ${CMAKE_BINARY_DIR} CACHE PATH "default install prefix" FORCE )
find_program(GENGETOPT gengetopt)

execute_process( COMMAND ${GENGETOPT} -i ${CMAKE_CURRENT_SOURCE_DIR}/ros_navigator.ggo --file-name ros_navigator_cmdline --output-dir=${CMAKE_CURRENT_BINARY_DIR} --unamed-opts )
execute_process( COMMAND ${GENGETOPT} -i ${CMAKE_CURRENT_SOURCE_DIR}/ros_simulator.ggo --file-name ros_simulator_cmdline --output-dir=${CMAKE_CURRENT_BINARY_DIR} --unamed-opts )




set( ROS_PATH_PREFIX /opt/ros/indigo CACHE PATH "default install prefix" FORCE )
find_path(ROS_INCLUDE_DIR NAMES ros/ros.h HINTS CMAKE_INSTALL_PREFIX/include)
include_directories(${ROS_INCLUDE_DIR} ${PROJECT_BINARY_DIR})
file( GLOB ROS_LIBRARIES "${ROS_PATH_PREFIX}/lib/*.so*" )

add_executable( ros_navigator ros_navigator.cpp ${CMAKE_CURRENT_BINARY_DIR}/ros_navigator_cmdline.c )
target_link_libraries( ros_navigator ${ROS_LIBRARIES} )
install( TARGETS ros_navigator DESTINATION bin )

add_executable( ros_simulator ros_simulator.cpp ${CMAKE_CURRENT_BINARY_DIR}/ros_simulator_cmdline.c )
target_link_libraries( ros_simulator ${ROS_LIBRARIES} )
install( TARGETS ros_simulator DESTINATION bin )

#GUI STUFF
# find the QT libraries
find_package(Qt4 COMPONENTS QtCore QtGui QtOpenGL REQUIRED)
find_package( OpenGL REQUIRED )
find_package( GLUT REQUIRED )

include(${QT_USE_FILE})
include_directories(${QT_INCLUDE_DIR} ${OPENGL_INCLUDE_DIR} )
# generate the MOC_SRCS from the QT header
qt_wrap_cpp(gui MOC_SRCS gui.h)
# generate the header and source files from the gengetopt file
execute_process(COMMAND ${GENGETOPT} -i ${CMAKE_CURRENT_SOURCE_DIR}/gui_process.ggo --file-name gui_process_cmdline --output-dir=${CMAKE_CURRENT_BINARY_DIR} --unamed-opts)
# generate the executable and link the libraries
add_executable(gui-process gui_process.cc gui.cc ${MOC_SRCS} ${CMAKE_CURRENT_BINARY_DIR}/gui_process_cmdline.c)
target_link_libraries(gui-process ${ROS_LIBRARIES} ${QT_LIBRARIES} ${OPENGL_LIBRARIES} )
install( TARGETS gui-process DESTINATION bin )